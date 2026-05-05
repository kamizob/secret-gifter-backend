CREATE TABLE room (
                      id SERIAL PRIMARY KEY,
                      code VARCHAR(10) UNIQUE
);

CREATE TABLE participant (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(100),
                             room_id INT REFERENCES room(id)
);

CREATE TABLE pair (
                      id SERIAL PRIMARY KEY,
                      giver VARCHAR(100),
                      receiver VARCHAR(100),
                      room_id INT REFERENCES room(id)
);