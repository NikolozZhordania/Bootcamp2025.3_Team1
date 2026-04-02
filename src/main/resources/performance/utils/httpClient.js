// HTTP client for the ATM & Branch list endpoint.
//
// Wraps the POST call, header construction, metric recording, and error logging
// so test scripts contain no request plumbing.
//
// Import with: import { postLocations } from './utils/httpClient.js';

import http   from 'k6/http';
import { check } from 'k6';
import { BASE_URL, LOCALE } from '../data/constants.js';

/**
 * POSTs to the locations list endpoint and records metrics.
 *
 * @param {object}   record       - { regionName, type } row from feederLoader
 * @param {object}   [metrics]    - optional { searchTrend, errorRate } from metrics.js
 *                                  pass null / omit for smoke test
 * @param {object}   [checks]     - map of check label → function(r) passed to k6 check()
 * @param {string}   [userAgent]  - value for User-Agent header (default: 'k6-test')
 * @param {boolean}  [minimalHeaders] - when true, only Content-Type is sent (smoke test mode)
 * @returns {object} the k6 Response object
 */
export function postLocations(record, {
    metrics       = null,
    checks        = { 'status is 200': (r) => r.status === 200 },
    userAgent     = 'k6-test',
    minimalHeaders = false,
} = {}) {
    const payload = JSON.stringify({
        regionName: record.regionName,
        type:       record.type,
        locale:     LOCALE,
    });

    const headers = minimalHeaders
        ? { 'Content-Type': 'application/json' }
        : {
            'Content-Type': 'application/json',
            'Accept':       'application/json',
            'User-Agent':   userAgent,
            'Origin':       'https://tbcbank.ge',
            'Referer':      'https://tbcbank.ge/',
        };

    const res = http.post(BASE_URL, payload, { headers });

    if (metrics) {
        metrics.searchTrend.add(res.timings.duration);
        metrics.errorRate.add(res.status !== 200);
    }

    check(res, checks);

    if (res.status !== 200) {
        console.log(`❌ ${res.status} for Region: ${record.regionName}`);
    }

    return res;
}