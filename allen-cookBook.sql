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
    rID				int NOT NULL Primary key auto_increment,
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





CREATE TABLE ingredient (
      recipeID VARCHAR(4), 
      rName VARCHAR(50), 
      amount INT, 
      unit VARCHAR(20)
);

CREATE TABLE owner (
      firstName VARCHAR(25), 
      lastName VARCHAR(25), 
      email VARCHAR(50), 
      userName VARCHAR(30) NOT NULL PRIMARY KEY, 
      password VARCHAR(25)
);

CREATE TABLE recipe (
        userName VARCHAR(30), 
        pushlishedDate DATE, 
        recipeName VARCHAR(30), 
        description VARCHAR(10000), 
        steps VARCHAR(10000), 
        recipeID VARCHAR(4) NOT NULL PRIMARY KEY, 
        Image VARCHAR
);








select * from recipe;

INSERT INTO recipe (rName, rImage, rDescription, rSteps, rType)
VALUES ('Beef Broccoli', 'beef-broccoli.JPG', 'This is a beef broccoli dish that is from Korea origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', 'Dinner');
INSERT INTO recipe (rName, rImage, rDescription, rSteps, rType)
VALUES ('Chicken Noodle Soup', 'chicken-noodle.JPG', 'This is a chicken noodle dish that is from Korea origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', 'Dinner');
INSERT INTO recipe (rName, rImage, rDescription, rSteps, rType)
VALUES ('Chicken Nuggets', 'chicken-nuggets.JPG', 'This is a chicken nugget dish that is from American origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', 'Lunch');
INSERT INTO recipe (rName, rImage, rDescription, rSteps, rType)
VALUES ('Corn Bread', 'corn-bread.JPG', 'This is a corn bread dish that is from American origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', 'Dinner');
INSERT INTO recipe (rName, rImage, rDescription, rSteps, rType)
VALUES ('Pizza', 'pizza.JPG', 'This is a pizza dish that is from Italian origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', 'Lunch Dinner');







INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2015-03-01', 'Beef Broccoli', 'This is a beef broccoli dish that is from Korea origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', '000', 'beef-broccoli.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2016-03-01', 'Chicken Noodle Soup', 'This is a chicken noodle dish that is from Korea origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', '001', 'chicken-noodle.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2017-03-01', 'Chicken Nuggets', 'This is a chicken nugget dish that is from American origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', '002', 'chicken-nuggets.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2017-01-01', 'Corn Bread', 'This is a corn bread dish that is from American origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', '003', 'corn-bread.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2013-04-01', 'Pizza', 'This is a pizza dish that is from Italian origin.', '1. dsfdnfaskjnjksandsjndnsakjdnsak 2. dfhdsfjhdsjkfhdkjshfdjshfksdhfksdj', '004', 'pizza.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2013-04-01', 'Chocolate Cake', 'asdfsdfsdfssda', '1.sdfsdfsdfdsf 2.dsfdsfsdfdsdfs 3.sdfsdfdsfsdfsdfsdfsdf', '005', 'chocolate.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image) 
VALUES ('test', '2012-11-03', 'Fresh Lobster Dish', 'RAWRRRRRRRRRRRR', '1. qeqwsdfsfsfds12321', '006', 'lobster.JPG');
INSERT INTO recipe (userName, pushlishedDate, recipeName, description, steps, recipeID, Image)  
VALUES ('test', '2010-03-03', 'Bulgogi Special', 'This is so delicious!', '1. 23212312312312313213', '007', 'bulgogi.JPG');

INSERT INTO morecookbook.owner (`firstName`, `lastName`, email, `userName`, password) 
    VALUES ('Allen', 'Muy', 'test@test.com', 'test', 'test');


INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit) 
    VALUES ('000', 'Ketchup', 5, 'grams');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('000', 'Bun', 1, 'bun');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('000', 'Hot Dog', 1, 'hot dog');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('000', 'Mustard', 5, 'grams');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('001', 'Patty', 1, 'patty');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('001', 'Bun', 1, 'bun');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('001', 'Lettuce', 5, 'grams');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('001', 'Cheese', 1, 'slice');

INSERT INTO morecookbook.ingredient (recipeID, rName, amount, unit)  
    VALUES ('002', 'Chocolate', 10, 'grams');

select * from recipe;
select * from owner;
select * from ingredient;































