CREATE TABLE tbc_locations (
    id INTEGER PRIMARY KEY,        -- Using the real IDs from your JSON (e.g., 803, 1690)
    name TEXT,                     -- "ბანკომატი" or "ვერის ფილიალი"
    location_desc TEXT,            -- Mapping from "location" key (e.g., "ჯი პი სი")
    address TEXT,                  -- "გრ. აბაშიძის ქ. #2"
    type TEXT,                     -- ATM, Branch, Cdm
    regionName TEXT,               -- "თბილისი"
    latitude REAL,
    longitude REAL,
    distance INTEGER,
    worksallday BOOLEAN,           -- 1 for true, 0 for false
    -- Currency Flags
    has_gel BOOLEAN DEFAULT 1,
    has_usd BOOLEAN DEFAULT 0,
    has_eur BOOLEAN DEFAULT 0,
    -- Working Hours (JSON uses a nested object, we flatten it for SQL)
    weekday_hours TEXT DEFAULT '10:00-18:00',
    saturday_hours TEXT DEFAULT '10:00-14:00'
);