package com.privateproperty.mapmarkets;


public class SchemaDB {
    public static final String NAME = "MapMarkets";

    public static final class MarketTable {
        public static final String NAME = "markets";

        public static final class Cols {
            public static final String UUID = "_id";
            public static final String LOGO = "urlLogo";
            public static final String MAP = "urlMap";
            public static final String NAME = "name";
            public static final String ADDRESS = "address";
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String CITY = "city";
            }
    }
    public static final class CategoriesTable {
        public static final String NAME = "categories";
        public static final class Cols {
            public static final String UUID = "_id";
            public static final String NAME = "name";
        }
    }
    public static final class ProductTable{
        public static final String NAME = "products";
        public static final class Cols{
            public static final String UUID = "id";
            public static final String NAME = "name";
            public static final String CATEGORY = "categoryID";
        }
    }
    public static final class LocationTable{
        public static final String NAME = "locations";
        public static final class Cols{
            public static final String UUID = "_id";
            public static final String MARKET = "marketID";
            public static final String CATEGORY = "catgoryID";
            public static final String COORDINATES = "coords";
        }
    }
    public static final class ListOfLists{
        public static final String NAME = "ListOfList";
        public static final class Cols{
            public static final String UUID = "_id";
            public static final String NAME = "name";
        }

    }
    public static final class ListToBuy{
        public static final String NAME = "ListToBuy";
        public static final class Cols{
            public static final String UUID = "_id";
            public static final String PARENTID = "parentid";
            public static final String NAME = "name";
            public static final String STATUS = "status";
            public static final String CATEGORY = "category";


        }

    }

//    public static final class AssortmentTable{
//        public static final String NAME = "assortment";
//        public static final class Cols{
//            public static final String UUID = "_id";
//            public static final String MARKET = "marketID";
//            public static final String PRODUCT = "productID";
//            }
//    }

}
