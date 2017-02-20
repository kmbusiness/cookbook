Create TABLE Owner  
( 
    firstName VARCHAR(25), 
    lastName VARCHAR(25), 
    email VARCHAR (50), 
    userName VARCHAR (30) PRIMARY KEY, 
);

Create Table Recipe 
( 
    userName varchar(30), 
    pushlishedDate date, 
    recipeName varchar(30), 
    description varchar(200),
    recipeID varchar(4) PRIMARY KEY,
    --Foreign Key 
    Foreign Key (userName) References Owner (userName)
); 

Create TABLE steps (
	description VARCHAR(30),
        recipeID varchar(4) PRIMARY KEY,
	Foreign Key(recipeID) References Recipe(recipeID)
);

Create TABLE Ingredient (
	recipeID varchar(4),
	amount varchar(30),
	name varchar(30) PRIMARY KEY,
	Foreign Key (recipeID) REFERENCES Recipe (recipeID)
);

