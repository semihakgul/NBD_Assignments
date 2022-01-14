printjson(
db.collection_1.aggregate(
   {"$unwind": "$credit"},
   {"$group": {
        "_id": "$credit.currency",
        "sum": { "$sum": { $toDecimal: "$credit.balance" } }
    }}
).toArray()
);