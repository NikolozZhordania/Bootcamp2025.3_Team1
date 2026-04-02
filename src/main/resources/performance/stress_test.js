import { sleep }                    from 'k6';
import { randomRecord }             from './utils/feederLoader.js';
import { searchTrend, errorRate }   from './utils/metrics.js';
import { postLocations }            from './utils/httpClient.js';

export const options = {
  stages: [
    { duration: '30s', target: 50  },
    { duration: '30s', target: 100 },
    { duration: '30s', target: 200 },
    { duration: '30s', target: 300 },
  ],
  thresholds: {
    http_req_failed:   ['rate<0.1'],
    http_req_duration: ['p(95)<5000'],
    search_duration:   ['p(95)<1500'],
    error_rate:        ['rate<0.01'],
  },
};

export default function () {
  postLocations(randomRecord(), {
    metrics:   { searchTrend, errorRate },
    userAgent: 'k6-stress-test',
    checks: {
      'status is 200':  (r) => r.status === 200,
      'body has results': (r) => r.body && r.body.length > 0,
    },
  });

  sleep(1);
}