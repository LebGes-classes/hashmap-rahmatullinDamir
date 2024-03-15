public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Kek", 21121);
        hashMap.put("girlfriend", 11);
        hashMap.put("helloworld", 221);
        hashMap.put("ukrf", 228);
        System.out.println(hashMap.get("ukrf"));
        System.out.println(hashMap.get("Kek"));
        System.out.println(hashMap.size());
        hashMap.remove("ukrf");
        System.out.println(hashMap.containsKey("ukrf"));

    }
}