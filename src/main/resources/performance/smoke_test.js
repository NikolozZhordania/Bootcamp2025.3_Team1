import { sleep }         from 'k6';
import { randomRecord }  from './utils/feederLoader.js';
import { postLocations } from './utils/httpClient.js';

export const options = {
  vus:      1,
  duration: '10s',
  thresholds: {
    http_req_duration: ['p(95)<1000'],
    'checks':          ['rate==1'],
  },
};

export default function () {
  postLocations(randomRecord(), {
    minimalHeaders: true,
    checks: {
      'status is 200':        (r) => r.status === 200,
      'response time < 1000ms': (r) => r.timings.duration < 1000,
      'valid response array': (r) => {
        try {
          const json = r.json();
          return Array.isArray(json) && json.length > 0 && json[0].id !== undefined;
        } catch (e) {
          return false;
        }
      },
    },
  });

  sleep(1);
}