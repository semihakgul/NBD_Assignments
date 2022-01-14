var mapFunction2 = function() {
      for (var idx = 0; idx < this.credit.length; idx++) {
         var key = this.credit[idx].currency;
         var value = { balance: parseFloat(this.credit[idx].balance) };
         emit(key, value);
      }
};

var reduceFunction2 = function(keycurr, countObjVals) {
   reducedVal = { Tolalbalance: 0 };
   for (var idx = 0; idx < countObjVals.length; idx++) {
         reducedVal.Tolalbalance += countObjVals[idx].balance;
        
   }
   return reducedVal;
};


db.people.mapReduce(
   mapFunction2,
   reduceFunction2,
   {
     out: "map_reduce_2" ,
     
   }

)

printjson(db.map_reduce_2.find().sort( { _id: -1 } ).toArray()
);
