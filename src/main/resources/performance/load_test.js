import { sleep }                    from 'k6';
import { randomRecord }             from './utils/feederLoader.js';
import { searchTrend, errorRate }   from './utils/metrics.js';
import { postLocations }            from './utils/httpClient.js';

export const options = {
  vus:      50,
  duration: '30s',
  thresholds: {
    search_duration: ['p(95)<1500'],
    error_rate:      ['rate<0.01'],
  },
};

export default function () {
  postLocations(randomRecord(), {
    metrics: { searchTrend, errorRate },
  });

  sleep(1);
}