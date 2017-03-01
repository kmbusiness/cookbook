Create table owner
(
	oLastName	varchar(10),
    oFirstName	varchar(10),
    oID			varchar(20) Primary key,
    oPassword	varchar(20),
    oEmail		varchar(40)
);
Create table recipe
(
	oID				varchar(20),
	rName			varchar(40),
    rID				char(4) Primary key,
    rImage			varchar(500),
    rDescription	text(40000),
    rSteps			text(40000),
    rType			varChar(100),
    foreign key (oID) references owner (oID)
);
Create table ingredient
(
	rID		char(4),
    iAmount	varchar(40),
    iType	varchar(40),
    foreign key (rID) references recipe (rID)
);

select * from recipe;