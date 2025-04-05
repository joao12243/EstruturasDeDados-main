public class UniBHList<T> {
    private Node<T> firstNode;
    private int totalElements;

    public void insertAtBeginning(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(firstNode);
        firstNode = newNode;
        totalElements++;
    }

    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null) {
            firstNode = newNode;
        } else {
            Node<T> current = firstNode;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        totalElements++;
    }

    public Node<T> removeAtBeginning() {
        if (firstNode == null) throw new RuntimeException("Lista vazia!");
        Node<T> aux = firstNode;
        firstNode = firstNode.getNext();
        totalElements--;
        return aux;
    }

    public Node<T> removeAtEnd() {
        if (firstNode == null) throw new RuntimeException("Lista vazia!");

        if (firstNode.getNext() == null) {
            Node<T> removed = firstNode;
            firstNode = null;
            totalElements--;
            return removed;
        }

        Node<T> current = firstNode;
        Node<T> previous = null;

        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }

        previous.setNext(null);
        totalElements--;
        return current;
    }

    // 1. Pesquisar item na lista
    public boolean contains(T value) {
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getValue().equals(value)) return true;
            current = current.getNext();
        }
        throw new RuntimeException("Elemento não encontrado na lista.");
    }

    // 2. Retirar item pelo valor da chave
    public void removeByValue(T value) {
        if (firstNode == null) throw new RuntimeException("Lista vazia.");

        if (firstNode.getValue().equals(value)) {
            firstNode = firstNode.getNext();
            totalElements--;
            return;
        }

        Node<T> current = firstNode;
        Node<T> previous = null;

        while (current != null && !current.getValue().equals(value)) {
            previous = current;
            current = current.getNext();
        }

        if (current == null) {
            throw new RuntimeException("Valor não encontrado.");
        }

        previous.setNext(current.getNext());
        totalElements--;
    }

    // 3. Verificar se a lista está vazia
    public boolean isEmpty() {
        return totalElements == 0;
    }

    // 4. Inserir após o i-ésimo item
    public void insertAfter(int index, T value) {
        if (index < 0 || index >= totalElements) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        Node<T> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        Node<T> newNode = new Node<>(value);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        totalElements++;
    }

    // 5. Remover o i-ésimo item
    public void removeAt(int index) {
        if (index < 0 || index >= totalElements) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        if (index == 0) {
            removeAtBeginning();
            return;
        }

        Node<T> current = firstNode;
        Node<T> previous = null;

        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.getNext();
        }

        previous.setNext(current.getNext());
        totalElements--;
    }

    // 6. Retornar o tamanho da lista
    public int size() {
        return totalElements;
    }

    // 7. Modificar um elemento presente na lista
    public void updateElement(T oldValue, T newValue) {
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getValue().equals(oldValue)) {
                current.setValue(newValue);
                return;
            }
            current = current.getNext();
        }
        throw new RuntimeException("Elemento a ser atualizado não foi encontrado.");
    }

    @Override
    public String toString() {
        if (this.totalElements == 0) {
            return "[ ]";
        }

        Node<T> currentNode = firstNode;
        StringBuilder builder = new StringBuilder("[");

        while (currentNode != null) {
            builder.append(currentNode.getValue());
            if (currentNode.getNext() != null) {
                builder.append(", ");
            }
            currentNode = currentNode.getNext();
        }

        builder.append("]");
        return builder.toString();
    }
}
