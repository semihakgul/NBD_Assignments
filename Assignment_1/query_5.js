db.collection_1.find(

	{birth_date:{
			"$gte": '2001-01-01',
			"$lt": '2100-12-31'

			}
		},
	{first_name:1,last_name:1,"location.city":1}

).sort({birth_date:1}).toArray()