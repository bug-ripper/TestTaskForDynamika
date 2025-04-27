CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       isbn VARCHAR(13) NOT NULL UNIQUE
);

CREATE TABLE clients (
                         id BIGSERIAL PRIMARY KEY,
                         full_name VARCHAR(255) NOT NULL,
                         birth_date DATE NOT NULL
);

CREATE TABLE rentals (
                         id BIGSERIAL PRIMARY KEY,
                         client_id BIGINT NOT NULL REFERENCES clients(id),
                         book_id BIGINT NOT NULL REFERENCES books(id),
                         rental_date TIMESTAMP NOT NULL
);

CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_rentals_client_book ON rentals(client_id, book_id);
CREATE INDEX idx_rentals_rental_date ON rentals(rental_date);