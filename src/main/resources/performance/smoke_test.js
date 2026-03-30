import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';

const testData = new SharedArray('locations', function () {
  return open('./performance_feeder.csv')
    .split('\n')
    .slice(1)
    .map(line => line.trim())
    .filter(line => line.length > 0)
    .map(line => {
      const [id, regionName, type] = line.split(',');
      return {
        regionName: regionName.replace(/"/g, '').trim(),
        type: type.trim(),
      };
    });
});

export const options = {
  vus: 1,
  duration: '10s',
  thresholds: {
    http_req_duration: ['p(95)<1000'],
    'checks': ['rate==1'],
  },
};

const BASE_URL = 'https://apigw.tbcbank.ge/api/v1/atmsAndBranches/list';

export default function () {
  const record = testData[Math.floor(Math.random() * testData.length)];

  const url = BASE_URL;
  const payload = JSON.stringify({
    regionName: record.regionName,
    type: record.type,
    locale: "ka-GE"
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);

  check(res, {
    'status is 200': (r) => r.status === 200,
    'response time < 1000ms': (r) => r.timings.duration < 1000,
    'valid response array': (r) => {
      try {
        const json = r.json();
        return Array.isArray(json) && json.length > 0 && json[0].id !== undefined;
      } catch (e) { 
        return false; 
      }
    },
  });

  sleep(1);
}