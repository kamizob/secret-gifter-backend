CREATE TABLE room (
                      id SERIAL PRIMARY KEY,
                      code VARCHAR(10) UNIQUE NOT NULL,
                      status VARCHAR(20) NOT NULL
);

CREATE TABLE participant (
                             id SERIAL PRIMARY KEY,
                             public_id UUID UNIQUE NOT NULL,
                             name VARCHAR(100) NOT NULL,
                             room_id INT REFERENCES room(id)
);

CREATE TABLE wishlist_item (
                               id SERIAL PRIMARY KEY,
                               participant_id INT REFERENCES participant(id),
                               item_text VARCHAR(300) NOT NULL
);

CREATE TABLE pair (
                      id SERIAL PRIMARY KEY,
                      giver_participant_id INT REFERENCES participant(id),
                      receiver_participant_id INT REFERENCES participant(id),
                      room_id INT REFERENCES room(id)
);