
db.collection_1.insert(
{
  sex: 'Male',
  first_name: 'Semih',
  last_name: 'Akgul',
  job: 'SWE',
  email: 'semih@gmail.com',
  location: {
    city: 'Warsaw',
    address: { streetname: 'Ryzowa', streetnumber: '1000' }
  },
  description: 'A fake description',
  height: '185',
  weight: '75',
  birth_date: '1996-10-04T10:00:00Z',
  nationality: 'Turkey',
  credit: [
   
    {
      type: 'instapayment',
      number: '3581609550731671',
      currency: 'PLN',
      balance: '2000'
    }
      
  ]
})