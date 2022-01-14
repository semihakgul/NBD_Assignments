var mapFunction2 = function() {
      for (var idx = 0; idx < this.credit.length; idx++) {
		 
		 if(this.sex=="Female" && this.nationality=="Poland") {
			 var key = this.credit[idx].currency ;
		     var value = { balance: parseFloat(this.credit[idx].balance) };
			 emit(key, value);
		 }
      }
};

var reduceFunction2 = function(keycurr, countObjVals) {
   reducedVal = { tolalBalance: 0, scount:0};
 
   for (var idx = 0; idx < countObjVals.length; idx++) {
         reducedVal.tolalBalance += countObjVals[idx].balance;
		 reducedVal.scount += 1;
   }
      
   return reducedVal;
};

var finalizeFunction2 = function(keycurr, countObjVals) {
   result = { tolalBalance: 0.00, avgBalance:0.00 };
 
   result.tolalBalance =   countObjVals.tolalBalance;
   cnt = countObjVals.scount;
   result.avgBalance =   countObjVals.tolalBalance / countObjVals.scount;
   
   return result;
};


db.people.mapReduce(
   mapFunction2,
   reduceFunction2,
   {
	
     out: "map_reduce_5x",
     finalize:finalizeFunction2
   }

)

printjson(db.map_reduce_5x.find().sort( { _id: 1 } ).toArray()
);
