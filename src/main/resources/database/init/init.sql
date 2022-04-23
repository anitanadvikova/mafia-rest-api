CREATE TABLE IF NOT EXISTS challenges
(
    id         UUID NOT NULL,
    gamers     UUID[],
    winner     UUID,
    start_date TIMESTAMP WITHOUT TIME ZONE,
    end_date   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_challenges PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS gamers
(
    id       UUID NOT NULL,
    picture  VARCHAR(32768),
    nickname VARCHAR(255),
    sex      VARCHAR(255),
    email    VARCHAR(255),
    CONSTRAINT pk_gamers PRIMARY KEY (id)
);

INSERT INTO gamers VALUES ('7539c5af-0b9c-480a-82dd-51a4fad409c0',
                           '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',
                           'anita', 'Transgender', 'anita@anita.com');
INSERT INTO challenges VALUES ('6151fe67-681a-c41a-66ae-5d41f5548c20',
                               array['8adc68e3-64f0-a5dd-242e-ba009ed2e767'::uuid, '7539c5af-0b9c-480a-82dd-51a4fad409c0'::uuid],
                               '7539c5af-0b9c-480a-82dd-51a4fad409c0',
                               '2022-04-20 15:39:17.382665+03',
                               '2022-04-20 15:55:34.964384+03'
                               );
INSERT INTO challenges VALUES ('c8a9ddb3-6aa4-44a7-9359-c0175420408c',
                               array['8adc68e3-64f0-a5dd-242e-ba009ed2e767'::uuid, '7539c5af-0b9c-480a-82dd-51a4fad409c0'::uuid],
                               '8adc68e3-64f0-a5dd-242e-ba009ed2e767',
                               '2022-04-20 15:39:17.382665+03',
                               '2022-04-20 16:30:34.964384+03'
                              );
INSERT INTO challenges VALUES ('4e3500fb-edbd-4015-9073-21dc3dbafd8e',
array['8adc68e3-64f0-a5dd-242e-ba009ed2e767'::uuid, '7539c5af-0b9c-480a-82dd-51a4fad409c0'::uuid],
'4e3500fb-edbd-4015-9073-21dc3dbafd8e',
'2022-04-20 15:39:17.382665+03',
'2022-04-20 16:40:34.964384+03'
);