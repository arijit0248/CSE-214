interface DatabaseQuery {
    void executeQuery(String query);
}

class SQLDatabase implements DatabaseQuery {
    @Override
    public void executeQuery(String sqlQuery) {
        System.out.println("Executing SQL query: " + sqlQuery);
    }
}

class NoSQLDatabase {
    public void runQuery(String noSQLQuery) {
        System.out.println("Executing NoSQL query: " + noSQLQuery);
    }
}

// ADAPTER — bridges the incompatible interfaces
class NoSQLAdapter implements DatabaseQuery {
    private NoSQLDatabase noSQLDatabase;

    public NoSQLAdapter(NoSQLDatabase noSQLDatabase) {
        this.noSQLDatabase = noSQLDatabase;
    }

    @Override
    public void executeQuery(String query) {
        noSQLDatabase.runQuery(query); // translates the call
    }
}

public class Main {
    public static void main(String[] args) {

        // New system uses NoSQL, but must conform to the legacy SQL interface
        DatabaseQuery db = new NoSQLAdapter(new NoSQLDatabase());
        
        // Client still writes SQL (legacy interface) —
        // adapter translates it to NoSQL internally
        db.executeQuery("SELECT * FROM users");
    }
}
