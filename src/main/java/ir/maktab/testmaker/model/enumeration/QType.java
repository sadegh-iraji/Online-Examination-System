package ir.maktab.testmaker.model.enumeration;

public enum QType {
    MCQ{
        @Override
        public String toString() {
            return "سوال چندگزینه ای";
        }
    }
    ,
    DQ{
        @Override
        public String toString() {
            return "سوال تشریحی";
        }
    }
}
