
-- default password is admin
INSERT INTO fiverr_dvneagu.users (id, created_at, updated_at, password, user_name, user_type) VALUES (1, '2019-07-31 11:34:32', null, '$2a$10$IAJ5kSGTV/RVuN30/Qit0.g5k5amRnuI497tXN5c/TP7R1aFreJZq', 'admin', 'ROLE_ADMIN');

INSERT INTO fiverr_dvneagu.rates (id, created_at, updated_at, from_date, interest_rate, penalties) VALUES (1, '2019-07-30 15:46:40', null, '2017-03-05', 0.4, 0);
INSERT INTO fiverr_dvneagu.rates (id, created_at, updated_at, from_date, interest_rate, penalties) VALUES (2, '2019-07-30 15:46:40', null, '2017-07-12', 0.5, 0.2);
INSERT INTO fiverr_dvneagu.rates (id, created_at, updated_at, from_date, interest_rate, penalties) VALUES (3, '2019-07-30 15:46:40', null, '2017-09-25', 0.3, 0.1);
INSERT INTO fiverr_dvneagu.rates (id, created_at, updated_at, from_date, interest_rate, penalties) VALUES (4, '2019-07-30 15:46:40', null, '2019-05-15', 0.1, 0.15);
INSERT INTO fiverr_dvneagu.rates (id, created_at, updated_at, from_date, interest_rate, penalties) VALUES (5, '2019-07-30 15:46:40', null, '2019-06-23', 0.2, 0);