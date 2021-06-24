create table if not exists Player (
    id varchar(32) primary key
);

create table if not exists Item (
    id varchar(32) primary key,
    priceGold int not null,
    priceSilver int not null
);

create table if not exists PlayerInventory (
    playerId varchar(32) not null,
    itemId varchar(32) not null,
    amount int not null,

    primary key (playerId, itemId),
    foreign key (playerId) references Player(id),
    foreign key (itemId) references Item(id)
);

create table if not exists PlayerBalance (
    playerId varchar(32),
    amountGold int not null,
    amountSilver int not null,

    primary key (playerId),
    foreign key (playerId) references Player(id)
);