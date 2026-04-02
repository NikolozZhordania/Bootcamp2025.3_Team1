// Custom k6 metrics shared across load and stress tests.
// Smoke test uses only built-in http_req_duration, so it does not import this.
//
// Import with: import { searchTrend, errorRate } from './utils/metrics.js';

import { Trend, Rate } from 'k6/metrics';

export const searchTrend = new Trend('search_duration');
export const errorRate   = new Rate('error_rate');