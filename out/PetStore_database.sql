	
		CREATE TABLE SellerContactInfo (
				contactInfoID VARCHAR,
				lastName VARCHAR,
				firstName VARCHAR,
				email VARCHAR,
		)
		CREATE TABLE Tag (
				tagID VARCHAR,
				items VARCHAR,
				tag VARCHAR,
				refCount VARCHAR,
		)
		CREATE TABLE Address (
				addressID VARCHAR,
				street1 VARCHAR,
				street2 VARCHAR,
				city VARCHAR,
				state VARCHAR,
				zip VARCHAR,
				latitude VARCHAR,
				longitude VARCHAR,
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
				grade VARCHAR,
				cf VARCHAR,
		)
		CREATE TABLE PayPalBean (
				postData VARCHAR,
		)
		CREATE TABLE ZipLocation (
				zipCode VARCHAR,
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
				price VARCHAR,
				address VARCHAR,
				contactInfo VARCHAR,
				totalScore VARCHAR,
				numberOfVotes VARCHAR,
				disabled VARCHAR,
				tags VARCHAR,
		)
		CREATE TABLE Product (
				productID VARCHAR,
				categoryID VARCHAR,
				name VARCHAR,
				description VARCHAR,
				imageURL VARCHAR,
		)
		CREATE TABLE CatalogFacade (
				emf VARCHAR,
				utx VARCHAR,
				bDebug VARCHAR,
		)
	

