package com.ghelidworks.android.mydictionarylite.database;

import java.util.UUID;

public class NounDbSchema {
    public static final class NounTable{
        public static final String NAME = "nouns";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String WORD = "word";
            public static final String TYPE = "type";
            public static final String TRANSLATE = "translate";
            public static final String PLURAL = "plural";
        }
    }

    public static final class VerbTable{
        public static final String NAME = "verbs";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String WORD = "word";
            public static final String TRANSLATE = "translate";
            public static final String TYPE = "type";
        }
        public static final class VerbInfinitivTable{
            public static final String NAME = "verb_infitiv";

            public static final class Cols{
                public static final String WERB_UUID = VerbTable.Cols.UUID;
                public static final String ICH = "ich";
                public static final String DU = "du";
                public static final String ER_SIE_ES = "er_sie_es";
                public static final String WIR = "wir";
                public static final String IHR = "ihr";
                public static final String SIE = "sie";
                public static final String CSIE = "csie";
            }
        }
        public static final class VerbPrateritumTable{
            public static final String NAME = "verb_prateritum";

            public static final class Cols{
                public static final String WERB_UUID = VerbTable.Cols.UUID;
                public static final String ICH = "ich";
                public static final String DU = "du";
                public static final String ER_SIE_ES = "er_sie_es";
                public static final String WIR = "wir";
                public static final String IHR = "ihr";
                public static final String SIE = "sie";
                public static final String CSIE = "csie";
            }
        }
        public static final class VerbPatrizipTable{
            public static final String NAME = "verb_patrizip";

            public static final class Cols{
                public static final String WERB_UUID = VerbTable.Cols.UUID;
                public static final String ICH = "ich";
                public static final String DU = "du";
                public static final String ER_SIE_ES = "er_sie_es";
                public static final String WIR = "wir";
                public static final String IHR = "ihr";
                public static final String SIE = "sie";
                public static final String CSIE = "csie";
            }
        }
    }


}
