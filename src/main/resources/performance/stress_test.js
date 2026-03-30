import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { Trend, Rate } from 'k6/metrics';

const searchTrend = new Trend('search_duration');
const errorRate = new Rate('error_rate');

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
  stages: [
    { duration: '30s', target: 50 }, 
    { duration: '30s', target: 100 },
    { duration: '30s', target: 200 },
    { duration: '30s', target: 300 },
  ],
  thresholds: {
    http_req_failed: ['rate<0.1'],
    http_req_duration: ['p(95)<5000'],
    search_duration: ['p(95)<1500'],
    error_rate: ['rate<0.01'],
  },
};

const BASE_URL = 'https://apigw.tbcbank.ge/api/v1/atmsAndBranches/list';

export default function () {
  // 2. RANDOM SELECTION FROM DATABASE
  const record = testData[Math.floor(Math.random() * testData.length)];

  const payload = JSON.stringify({
    regionName: record.regionName,
    type: record.type,
    locale: 'ka-GE',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'User-Agent': 'k6-stress-test',
      'Origin': 'https://tbcbank.ge',
      'Referer': 'https://tbcbank.ge/',
    },
  };

  const res = http.post(BASE_URL, payload, params);

  // 3. METRIC UPDATES
  searchTrend.add(res.timings.duration);
  errorRate.add(res.status !== 200);

  check(res, {
    'status is 200': (r) => r.status === 200,
    'body has results': (r) => r.body && r.body.length > 0,
  });

  if (res.status !== 200) {
    console.log(`❌ Error ${res.status} for Region: ${record.regionName}`);
  }

  sleep(1);
}