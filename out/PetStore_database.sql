		CREATE TABLE SellerContactInfo (
					contactInfoID VARCHAR,
					
					lastName VARCHAR,
					
					firstName VARCHAR,
					
					email VARCHAR,
					
		)
		CREATE TABLE Tag (
					tagID INT,
					
					items  VARCHAR,
					
					tag VARCHAR,
					
					refCount INT,
					
		)
		CREATE TABLE Address (
					addressID VARCHAR,
					
					street1 VARCHAR,
					
					street2 VARCHAR,
					
					city VARCHAR,
					
					state VARCHAR,
					
					zip VARCHAR,
					
					latitude  VARCHAR,
					
					longitude  VARCHAR,
					
					COMMA VARCHAR,
					
		)
		CREATE TABLE FileUploadResponse (
					itemId VARCHAR,
					
					productId VARCHAR,
					
					message VARCHAR,
					
					status VARCHAR,
					
					duration VARCHAR,
					
					durationString VARCHAR,
					
					startDate VARCHAR,
					
					endDate VARCHAR,
					
					uploadSize VARCHAR,
					
					thumbnail VARCHAR,
					
		)
		CREATE TABLE Category (
					categoryID VARCHAR,
					
					name VARCHAR,
					
					description VARCHAR,
					
					imageURL VARCHAR,
					
		)
		CREATE TABLE RatingBean (
					itemId VARCHAR,
					
					grade INT,
					
					cf  VARCHAR,
					
		)
		CREATE TABLE PayPalBean (
					postData  VARCHAR,
					
		)
		CREATE TABLE ZipLocation (
					zipCode INT,
					
					city VARCHAR,
					
					state VARCHAR,
					
		)
		CREATE TABLE Item (
					itemID VARCHAR,
					
					productID VARCHAR,
					
					name VARCHAR,
					
					description VARCHAR,
					
					imageURL VARCHAR,
					
					imageThumbURL VARCHAR,
					
					price  VARCHAR,
					
					address  VARCHAR,
					
					contactInfo  VARCHAR,
					
					totalScore INT,
					
					numberOfVotes INT,
					
					disabled INT,
					
					tags  VARCHAR,
					
		)
		CREATE TABLE Product (
					productID VARCHAR,
					
					categoryID VARCHAR,
					
					name VARCHAR,
					
					description VARCHAR,
					
					imageURL VARCHAR,
					
		)
		CREATE TABLE CatalogFacade (
					emf  VARCHAR,
					
					utx  VARCHAR,
					
					bDebug  VARCHAR,
					
		)
