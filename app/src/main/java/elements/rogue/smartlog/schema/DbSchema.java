package elements.rogue.smartlog.schema;

public class DbSchema {

    public static final class LogTable{
        public static final String NAME = "logs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SITUATION = "situation";
            public static final String DESCRIPTION = "description";
            public static final String COMMENT = "comment";
            public static final String DATE = "date";
        }
    }
}
