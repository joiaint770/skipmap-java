package prog10;
import java.util.Map;
import java.util.Random;

public class SkipMap<K extends Comparable<K>, V> extends LinkedMap<K, V> {
    // SkipMap containing half the elements chosen at random.
    SkipMap<K, Entry> skip;

    // Coin flipping code.
    Random random = new Random(1);
    /** Flip a coin.
     * @return true if you flip heads.
     */
    boolean heads () {
        return random.nextInt() % 2 == 0;
    }

    protected void add (Entry nextEntry, Entry newEntry) {
        super.add(nextEntry, newEntry);
        K key = newEntry.key;

       //To get start, find key in skip.  Call it skipEntry.  If skipEntry is
        //not null, then its value is the entry in the bottom list that is the
        //last lucky entry before key, and so the next of that entry is start.
        //If skipEntry is null, then there is no lucky entry before the key, and
        //we set start to first.
        //
        //To get ender, get the next entry in skip after skipEntry.  Call it
        //skipNext. However, if skipEntry was null, then set skipNext to
        //skip.first.  Set ender to the value of skipEntry, unless it is null.
        //In that case, set ender to null.

       /* if(skip==null){
            skip = new SkipMap<>();
        }

        */
        Entry start;
        Entry skipEntry;
        Entry ender;


        if(skip==null){
            start = first;
            ender = null;

        }else{

             skipEntry = (LinkedMap<K, V>.Entry) skip.find(key);
            if(skipEntry == null){
                start = first;
                ender = (LinkedMap<K, V>.Entry) skip.first.value;
            }else{
                start = (LinkedMap<K, V>.Entry) skipEntry.getValue();
                if(skipEntry.next == null){
                    ender = null;
                }else{
                    ender = (LinkedMap<K, V>.Entry) skipEntry.next.value;
                }

            }
        }



        /*Entry skipEntry = start;
        if(skipEntry != null){
            start = skipEntry.next;
        }
        if(skipEntry == null){
            start = first;
        }

        Entry skipNext = newEntry;


        if(skipEntry != null){
            skipNext = skipEntry.next;
        }else{
            if(skip == null){
                skipNext = null;
            }else{
                skipNext = (LinkedMap<K, V>.Entry) skip.first;
            }


        }

         */








        /*Entry ender = newEntry;

        if(skipEntry!= null){
             ender = skipNext;
        }

        if(skipEntry == null){
             ender = null;
        }

         */






            //skip.put(newEntry.key, newEntry);
            //If there are
            //three unlucky entries in a row, then put the middle one into skip.
            int count = 0;
            Entry previous = null;

            for(Entry entry = start; entry!= ender; entry = entry.next){
                count ++;



                if(count ==3){
                    if(skip!= null){
                        
                         skip.put(entry.previous.key, entry.previous);
                        //System.out.println("key: "+entry.previous.key);
                    }else{
                        skip = new SkipMap<>();

                         skip.put(entry.previous.key, entry.previous);
                         //entry.previous =
                    }
                }
                previous = entry;
                


            }
        }





        ///

    protected Entry find (K key) {
        Entry entry = first;
        // EXERCISE
        // Call find for the key in skip.

        // Set entry to the value of that Entry in skip.
        // Check for null so you don't crash.
        ///
        if(skip!= null){
            LinkedMap<K, LinkedMap<K,V>.Entry>.Entry skipEntry = skip.find(key);
            if(skipEntry!= null){
                entry= skipEntry.value;
            }
        }

        for(Entry ent = entry; ent!=null; ent=ent.next){
            if(ent.key.equals(key)){
                return ent;
            }
            if(ent.key.compareTo(key)>0){
                return ent.previous;
            }
        }


        ///

        // EXERCISE
        // Use the same search as in LinkedMap.find,
        // but use the current value of entry
        // instead of starting at first.
        ///

        ///

        return last;
    }

    protected void remove (Entry entry) {
        super.remove(entry);

        if(skip!=null){
            skip.remove(entry.key);

            if(skip.first == null){
                skip = null;
            }
        }
        K key = entry.key;
        //When removing an Entry, recursively (public) remove it from the
        //   inner skiplist.  If you removed a lucky Entry, there might be three
        //   or four unlucky in a row.

        /*if(skip!=null){
            skip.remove(entry.key);

            if(skip.first == null){
                skip = null;
            }
        }


        if(true){
            return;
        }

         */


        /*Entry start = find(key);
        if(skip==null){
             start = null;

        }else{
             start = (LinkedMap<K, V>.Entry) skip.find(key);
        }




        Entry skipEntry = start;
        if(skipEntry != null){
            start = skipEntry.next;
        }
        if(skipEntry == null){
            start = first;
        }

        Entry skipNext = entry;




        if(skipEntry != null){
            skipNext = skipEntry.next;
        }else{
            if(skip == null){
                skipNext = null;
            }else{
                skipNext = (LinkedMap<K, V>.Entry) skip.first;
            }


        }






        Entry ender = entry;

        if(skipEntry!= null){
            ender = skipNext;
        }

        if(skipEntry == null){
            ender = null;
        }

         */



        Entry start;
        Entry skipEntry;
        Entry ender;


        if(skip==null){
            start = first;
            ender = null;

        }else{

            skipEntry = (LinkedMap<K, V>.Entry) skip.find(key);
            if(skipEntry == null){
                start = first;
                ender = (LinkedMap<K, V>.Entry) skip.first.value;
            }else{
                start = (LinkedMap<K, V>.Entry) skipEntry.getValue();
                if(skipEntry.next == null){
                    ender = null;
                }else{
                    ender = (LinkedMap<K, V>.Entry) skipEntry.next.value;
                }

            }
        }


            //skip.put(newEntry.key, newEntry);
            //If there are
            //three unlucky entries in a row, then put the middle one into skip.
            int count = 0;
            Entry previous = null;




           Entry temp;
            for( temp = start; temp!= ender; temp = temp.next) {

                count++;
            }
            if(temp == null){
                temp = last;
            }

        if (count == 3) {
            if (skip != null) {
                temp.previous = skip.put(temp.previous.key, temp.previous);

            } else {
                skip = new SkipMap<>();
                temp.previous = skip.put(temp.previous.key, temp.previous);
            }
        }

        if (count == 4) {
            if (skip != null) {
                temp.previous.previous = skip.put(temp.key, temp.previous);

            } else {
                skip = new SkipMap<>();
                temp.previous.previous = skip.put(temp.key, temp.previous);
            }
        }

        if(count == 0 && skip != null){
            if(temp != null){
                temp = skip.remove(temp.key);
            } else {
               skip.remove(skip.last.key);
            }
        }

        if(skip != null && skip.first == null){
            skip = null;
        }


        //If you remove an unlucky Entry, you might end up with zero lucky
        //   Entry in a row.  Make the first lucky entry after the key unlucky.
        //   (Or if there is no such entry, make the first lucky entry before
        //   the key unlucky.  Or if there is no such entry either, then your
        //   map must be empty, so you are done.)

        /*if(count == 0){
            if(entry.next != null){
                skip.remove(entry.next);
            }else if (entry.previous != null){
                skip.remove(entry.previous);
            }

        }

         */





        ///
    }


    public String toString () {
        if (skip == null)
            return super.toString();
        return skip.toString() + "\n" + super.toString();
    }

    public static void main (String[] args) {
        Map<String, Integer> map = new SkipMap<String, Integer>();
        test(map);
        String[] keys = { "Vic", "Ira", "Lisa", "Joia", "Esther" };
        for (int i = 0; i < keys.length; i++) {
            System.out.print("put(" + keys[i] + ", " + i + ") = ");
            System.out.println(map.put(keys[i], i));
            System.out.println(map);



            System.out.print("get(" + keys[i] + ") = ");
            System.out.println(map.get(keys[i]));

            System.out.print("remove(" + keys[i] + ") = ");
            System.out.println(map.remove(keys[i]));
            System.out.println(map);

            System.out.print("put(" + keys[i] + ", " + i + ") = ");
            System.out.println(map.put(keys[i], i));
            System.out.println(map);
            System.out.println("");



        }
    }
}

