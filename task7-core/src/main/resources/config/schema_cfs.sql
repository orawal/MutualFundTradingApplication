CREATE TABLE IF NOT EXISTS Employee (
  id        INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userName  VARCHAR(32) NOT NULL,
  password  VARCHAR(256)                     DEFAULT NULL,
  salt      INT                              DEFAULT 0,
  firstName VARCHAR(32) NOT NULL,
  lastName  VARCHAR(32) NOT NULL,
  type      TINYINT                          DEFAULT 0,
  status    TINYINT                          DEFAULT 0,
  createdAt TIMESTAMP                        DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP                        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY userNameUniqueKey (`userName`)
);

INSERT IGNORE INTO Employee
VALUES (NULL, 'jeff', 'teamnine', 0, 'Jeffrey', 'Eppinger', 1, 0, NULL, NULL);


CREATE TABLE IF NOT EXISTS Customer (
  id                INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userName          VARCHAR(32)  NOT NULL,
  firstName         VARCHAR(32)  NOT NULL,
  lastName          VARCHAR(32)  NOT NULL,
  addressLine1      VARCHAR(256) NOT NULL,
  city              VARCHAR(256) NOT NULL,
  state             VARCHAR(32)  NOT NULL,
  zipcode           VARCHAR(32)  NOT NULL,
  salt              INT                               DEFAULT 0,
  password          VARCHAR(256)                      DEFAULT NULL,
  addressLine2      VARCHAR(256)                      DEFAULT NULL,
  cash              BIGINT                            DEFAULT 0,
  cashToBeDeposited BIGINT                            DEFAULT 0,
  cashToBeChecked   BIGINT                            DEFAULT 0,
  status            TINYINT                           DEFAULT 0,
  createdAt         TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP,
  updatedAt         TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY userNameUniqueKey (`userName`)
);

CREATE TABLE IF NOT EXISTS Fund (
  id                INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  fundName          VARCHAR(256) NOT NULL,
  lastTransitionDay TIMESTAMP    NULL,
  symbol            VARCHAR(256)                      DEFAULT NULL,
  comment           VARCHAR(512)                      DEFAULT NULL,
  lastPrice         BIGINT                            DEFAULT 0,
  status            TINYINT                           DEFAULT 0,
  createdAt         TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP,
  updatedAt         TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY fundNameUniqueKey (`fundName`)
);

CREATE TABLE IF NOT EXISTS FundPriceHistory (
  id        INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  fundId    INT       NOT NULL,
  price     INT                            DEFAULT 0,
  priceDate TIMESTAMP NULL,
  status    TINYINT                        DEFAULT 0,
  createdAt TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP,
  updatedAt TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (fundId) REFERENCES Fund (id)
);

CREATE TABLE IF NOT EXISTS Position (
  id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  customerId INT NOT NULL,
  fundId     INT NOT NULL,
  shares     BIGINT                   DEFAULT 0,
  status     TINYINT                  DEFAULT 0,
  createdAt  TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  updatedAt  TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (fundId) REFERENCES Fund (id),
  FOREIGN KEY (customerId) REFERENCES Customer (id)
);

CREATE TABLE IF NOT EXISTS Transition (
  id          INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  customerId  INT       NOT NULL,
  fundId      INT       NULL,
  positionId  INT       NULL,
  executeDate TIMESTAMP NULL,
  shares      BIGINT                         DEFAULT 0,
  type        TINYINT                        DEFAULT 0,
  amount      BIGINT                         DEFAULT 0,
  status      TINYINT                        DEFAULT 0,
  createdAt   TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP,
  updatedAt   TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (customerId) REFERENCES Customer (id)
);


CREATE OR REPLACE VIEW TransitionView AS
  SELECT
    t.id,
    t.customerId,
    t.fundId,
    t.positionId,
    t.executeDate,
    t.shares,
    t.type,
    t.amount,
    t.status,
    t.createdAt,
    t.updatedAt,
    f.fundName,
    CONCAT(c.firstName, " ", c.lastName) AS displayName
  FROM Transition AS t
    LEFT JOIN Fund AS f ON f.id = t.fundId
    LEFT JOIN Customer AS c ON c.id = t.customerId;

CREATE OR REPLACE VIEW FundPriceHistoryView AS
  SELECT
    fph.id,
    fph.fundId,
    fph.price,
    fph.priceDate,
    f.fundName
  FROM FundPriceHistory AS fph
    LEFT JOIN Fund AS f ON f.id = fph.fundId;


CREATE OR REPLACE VIEW PositionView AS
  SELECT
    p.id,
    p.customerId,
    p.fundId,
    p.shares,
    p.status,
    p.createdAt,
    p.updatedAt,
    f.fundName,
    f.lastPrice,
    t.amount,
    c.userName
  FROM Position AS p
    LEFT JOIN Fund AS f ON f.id = p.fundId
    LEFT JOIN Transition AS t ON t.positionId = p.id
    LEFT JOIN Customer AS c ON c.id = p.customerId;

#There should not be any comment in this file unless it is the end of the file.
