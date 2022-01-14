import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;

import java.util.concurrent.ExecutionException;

public class Main {

    static class Fish {
        public String name;
        public String importedFrom;
        public double importPrice;
        public double salePrice;
        public Boolean isInStock;
    }

    public static void main( String[] args ) {

        try{
            // Riak client
            RiakClient client = RiakClient.newClient(8087, "localhost");

            Fish fish = new Fish();
            fish.importPrice=2.5;
            fish.salePrice=5.5;
            fish.importedFrom ="Turkey";
            fish.isInStock=Boolean.TRUE;
            fish.name ="guppy";

            String key="1";
            String bucket="s24084";

            String response="";

            // add a document to the database
            createRecord(fish, key, bucket, client);

            // retrieve it
            response = retrieveRecord(key, bucket, client);

            ///print it out
            System.out.println("Retrieved record:");
            System.out.println(response);

            // modify it (and update DB with a new version)
            fish.salePrice =6.5;
            createRecord(fish, key, bucket, client);

            // retrieve it again
            response = retrieveRecord(key, bucket, client);

            // print retrieved data
            System.out.println("Updated record:");
            System.out.println(response);

            // delete it from the database
            deleteRecord(key, bucket, client);

            // try to retrieve it again
            response = retrieveRecord(key, bucket, client);
            System.out.println("Try to retrieve deleted data:");
            System.out.println(response);

            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteRecord(String key, String bucket, RiakClient client) throws ExecutionException, InterruptedException {
        Location location = new Location(new Namespace(bucket), key);
        DeleteValue deleteOperation = new DeleteValue.Builder(location).build();
        client.execute(deleteOperation);
    }

    private static String createRecord(Fish person, String key, String bucket, RiakClient client) throws ExecutionException, InterruptedException {
        Location location = new Location(new Namespace(bucket), key);
        StoreValue storedValue = new StoreValue.Builder(person).withLocation(location).build();
        StoreValue.Response svResponse = client.execute(storedValue);

        return svResponse.toString();
    }

    private static String retrieveRecord(String key, String bucket, RiakClient client) throws ExecutionException, InterruptedException {
        Location location = new Location(new Namespace(bucket), key);
        FetchValue fetchedValue = new FetchValue.Builder(location).build();
        FetchValue.Response response = client.execute(fetchedValue);
        
        return response.getValue(String.class);
    }
}
