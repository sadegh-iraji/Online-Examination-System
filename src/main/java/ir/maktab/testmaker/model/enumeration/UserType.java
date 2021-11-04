package ir.maktab.testmaker.model.enumeration;

public enum UserType {
    MANAGER{
        @Override
        public String toString() {
            return "مدیر";
        }
    }, TEACHER {
        @Override
        public String toString() {
            return "استاد";
        }
    },STUDENT{
        @Override
        public String toString() {
            return "دانشجو";
        }
    }

}
