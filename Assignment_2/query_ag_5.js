printjson(
db.collection_1.aggregate(
{
"$match": {
    "$and":[
     {"nationality":"Poland"},
     {"sex":"Female"} 
    ]
  }
},
   {"$unwind": "$credit"},
   {"$group": {
        "_id": "$credit.currency",
        "sum": { "$sum": { $toDecimal: "$credit.balance" } },
		"Avg": { "$avg": { $toDecimal: "$credit.balance" } }
    }}
).toArray()
);