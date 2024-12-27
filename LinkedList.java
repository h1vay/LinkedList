public class LinkedList {

       /*
->    - - - Den Knoten class erstellen - - -
    1. Ein Baukasten für Knoten erstellen (beinhaltet data und Zeiger für nächsten Knoten)
    2. Knoten initialisieren, dass der Knoten den übergebenen data übernimmt und der Zeiger auf nächsten bzw. leeren Knoten zeigt

->    - - - Head Node erstellen - - -
    3. Head Knoten erstellen

->    - - - Head Node in LinkedList hinzufügen - - -
    4. Dieser Head Knoten wird in unser Baukasten für LinkedList anschließend hinzugefügt, erstelle aber erstmal Baukasten dafür
    5. Den Head Knoten in die verkettete Liste hinzufügen bzw. hinein initialisieren
     */

    //------------------------------
    //Baukasten für einen Knoten
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    //------------------------------

    //Eigentliche Linked Liste
    private Node head;
    private LinkedList() {
        this.head = null; //Standard-Wert, da wir erst jetzt beginnen und unsere Daten noch nicht existieren
    }

    //----- ADD METHODEN -----//
    //Jetzt erstellen wir Knoten und ihre Referenzen, um sie miteinander zu verketten
    public void addFirst (int data) {
        //neuer Knoten
        Node knoten = new Node(data);

        //***ALGORITHUS***
        //Jetzt weisen wir den Knoten next auf head hinzu erstmal und danach ersetzen wir head mit knoten
        knoten.next = head;
        head = knoten;
    }

    public void addLast (int data) {
        Node knoten = new Node(data);
        Node exist = head;

        //Falls Liste Leer ist
        if (head == null) {
            head = knoten;
            return;
        }

        //***ALGORITHMUS***
        while (exist.next != null) { //Wenn nächster Wert nicht null ist, dann verschiebe exist Node so lange, bis du endlich null erreichst
            exist = exist.next; //Such-Knoten wird nun auf den letzten Knoten geupdated, der auf null verweist und somit letzter Knoten ist
        }
        exist.next = knoten; //dieser Such-Knoten bzw. der letzte Knoten, der leer ist, referenziert nun auf unser knoten
    }

    public void addAt (int index, int data) {
        Node exist = head;
        Node knoten = new Node(data);

        if (index == 0) { //wenn index schon am anfang direkt ist
            addFirst(data);
            return;
        }

        //***ALGORITHMUS***
        //Neuer Knoten wird an einem bestimmten Index eingefügt
        for (int i = 0; i < index - 1; i++) {
            if (exist == null) { //Falls SuchKnoten nicht existiert, d.h. es existiert gar kein Knoten in der Liste
                return; //Abbruch
            }
            exist = exist.next; //Zeiger zeigt nun auf den Knoten für nachher
        }
        knoten.next = exist.next; //Knoten stellt sich wie ein Mensch vor den alten Knoten und verweist auf den next Knoten vom ursprünglichen Knoten
        exist.next = knoten; //der ursprüngliche Knoten verweist somit auf unser neu erstellten Knoten
    }

    //----- DELETE METHODEN -----//

    public void deleteFirst() {
        /*
        if (head == null) {
            return;
        }
         */

        head = head.next; //head wird gelöscht und verweist nun auf head.next
    }

    public void deleteLast() {
        Node exist = head;

        //Falls Liste leer ist
        if (head == null) {
            return;
        }

        //Falls Liste nur den head Knoten hat
        if (head.next == null) {
            head = null;
            return;
        }

        //***ALGORITHMUS***
        //Hier gehen wir durch die Liste so lange durch, bis der next zeiger auf null zeigt UND der next vom next vom zeiger ebenfalls
        //auf null zeigt, weil dann wären wir uns sicher, dass dies wirklich der letzte Knoten ist
        if (exist.next != null && exist.next.next != null) {
            exist = exist.next; //ZeigerKnoten wird aktualisiert und nimmt den nächsten Knoten an
        }
        exist.next = null; //Der nächste Knoten vom aktualisierten Knoten ist nun gelöscht
    }

    public void deleteAtIndex(int index) {
        Node exist = head;

        for (int i = 1; i < index - 1; i++) {
            exist = exist.next;
        }
        if (exist.next != null) {
            exist.next = exist.next.next; //Knoten stellt sich wie ein Mensch vor den alten Knoten und verweist auf den next Knoten vom ursprünglichen Knoten
        }
    }

    public int get(int index) {
        Node exist = head;

        for (int i = 0; i < index; i++) {
            exist = exist.next;
        }
        return exist.data;
    }

    public int size() {
        int zaehler = 1;
        Node exist = head; //Zähler-Knoten

        while (exist.next != null) {
            zaehler++;
            exist = exist.next;
        }
        return zaehler;
    }

    public static void listString(LinkedList list) {
        System.out.print("Inhalt der Liste: ");
        Node current = list.head; // Zugriff auf den Kopf der Liste
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null"); // Zeigt das Ende der Liste an

        // Größe der Liste ausgeben
        System.out.println("Größe der Liste: " + list.size());
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addFirst(3);
        list.addFirst(-1);
        list.addFirst(9);
        list.addFirst(48);
        list.addFirst(16);
        list.addFirst(31);

        listString(list);
        list.addLast(99);

        listString(list);
        list.size();
        list.deleteAtIndex(2);
        list.addAt(2,34231);
        list.size();
        listString(list);
    }
}