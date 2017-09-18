package lista;

import java.util.Scanner;

public class Lista {

    /**
     * Autor: Nicolas Cunha
     * <p>
     * Fazer um programa para a realização das seguintes
     * operações com uma lista encadeada simples não circular:
     * <p>
     * - incluir um objeto no início na lista
     * <p>
     * - incluir um objeto no final da lista
     * <p>
     * - verificar se um objeto específico está na lista
     * <p>
     * - remover um objeto específico da lista
     * <p>
     * - remover o primeiro objeto da lista
     * <p>
     * - remover o último objeto da lista
     * <p>
     * - mostrar todos os objetos da lista na ordem do primeiro para o último
     * <p>
     * - mostrar todos os objetos da lista na ordem do último para o primeiro
     */

    static Time inicio = null;
    static Time fim = null;
    static int contCelulas = 0;

    public static Time iniciaTimes() {
        Time t = new Time();
        t.setEstrela(pedeInt("Informe a id do personagem, de 1 a 10000:", 1, 10000));
        t.setNome(pedeStr("Informe o nome do personagem, de 1 a 100 caracteres: ", 1, 100));
        return t;
    }

    public static void insereSemLista() {
        inicio = iniciaTimes();
        fim = inicio;
        inicio.setNext(fim);
        fim.setNext(null);
        contCelulas++;
    }

    public static void insereInicio() {
        if (listaVazia()) {
            System.out.println("A lista está vazia. Iniciando uma lista nova.");
            insereSemLista();
        } else {
            Time t = iniciaTimes();
            t.setNext(inicio);
            inicio = t;
        }
    }

    public static void insereFim() {
        if (listaVazia()) {
            System.out.println("A lista está vazia. Iniciando uma lista nova.");
            insereSemLista();
        } else {
            Time t = iniciaTimes();
            fim.setNext(t);
            fim = t;
        }
    }

    public static void verificaExiste() {
        boolean exist = false;
        Time atual;
        switch (pedeInt("1 - Procurar por ID \n 2 - Procurar por nome", 1, 2)) {
            case 1:
                int estrela = pedeInt("Informe a id do personagem, de 1 a 10000:", 1, 10000);
                atual = inicio;
                while(atual != null){
                    if (atual.getEstrela() == estrela) {
                        exist = true;
                        break;
                    }
                    atual = atual.getNext();
                }
                break;
            case 2:
                String nome = pedeStr("Informe o nome do personagem, de 1 a 100 caracteres: ", 1, 100);
                atual = inicio;
                while(atual != null){
                    if (atual.getNome().equals(nome)) {
                        exist = true;
                        break;
                    }
                    atual = atual.getNext();
                }
                break;
        }
        if(exist) {
            System.out.println("Existe!");
        }else{
            System.out.println("Não existe!");
        }
    }


    public static Time buscarAnterior(Time t){
        Time ret = null;
        Time atual = inicio;
        while(atual != null){
            if(atual.getNext() == t){
                ret = atual;
                break;
            }
            atual = atual.getNext();
        }
        return ret;
    }



    public static void removeEspecifico() {
        Time atual;
        switch (pedeInt("1 - Remover por ID \n 2 - Remover por nome", 1, 2)) {
            case 1:
                int estrela = pedeInt("Informe a id do personagem, de 1 a 10000:", 1, 10000);
                if(estrela == inicio.getEstrela()){
                    removeInicio();
                }else if(estrela == fim.getEstrela()){
                    removeFim();
                }else{
                    atual = inicio;
                    while(atual != null){
                        if (atual.getEstrela() == estrela) {
                            //ligar o anterior ao próximo de atual
                            Time anterior = buscarAnterior(atual);
                            anterior.setNext(atual.getNext());

                            break;
                        }
                        atual = atual.getNext();
                    }

                }
                break;
            case 2:
                String nome = pedeStr("Informe o nome do personagem, de 1 a 100 caracteres: ", 1, 100);
                if(nome.equals(inicio.getNome())){
                    removeInicio();
                }else if(nome.equals(fim.getNome())){
                    removeFim();
                }else{
                    atual = inicio;
                    while(atual != null){
                        if (atual.getNome().equals(nome)) {
                            //ligar o anterior ao próximo de atual
                            Time anterior = buscarAnterior(atual);
                            anterior.setNext(atual.getNext());

                            break;
                        }
                        atual = atual.getNext();
                    }

                }
                break;
        }
    }

    public static void removeInicio() {
        if(listaVazia()){
            System.out.println("Lista vazia.");
        }else{
            inicio = inicio.getNext();
        }
    }

    public static void removeFim() {
        if(listaVazia()){
            System.out.println("Lista vazia.");
        }else{
            if(inicio == fim){
                inicio = null;
                fim = null;
            }else{
                Time atual = inicio;
                while(atual != null){
                    if(atual.getNext().getNext() == null){
                        atual.setNext(null);
                        fim = atual;
                        break;
                    }
                    atual = atual.getNext();
                }
            }

        }
    }

    public static void listaInicioFim() {
        if(listaVazia()){
            System.out.println("Lista vazia.");
        }else{
            Time atual = inicio;
            while(atual != null){
                System.out.println(atual.getEstrela() + " - " + atual.getNome());
                atual = atual.getNext();
            }
        }
    }

    public static void listaFimInicio() {
        if(listaVazia()){
            System.out.println("Lista vazia: ");
        }else{
            Time atual = fim;
            while(atual != null){
                System.out.println(atual.getEstrela() + " - "+atual.getNome());
                if(atual != inicio){
                    atual = buscarAnterior(atual);
                }else{
                    atual = null;
                }
            }
        }
    }

    public static boolean listaVazia() {
        return inicio == null;
    }

    public static int pedeInt(String msg, int min, int max) {
        int ret = min - 1;
        boolean erro;
        do {
            erro = false;
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println(msg);
                ret = scan.nextInt();
                if (ret < min || ret > max)
                    System.out.println("Você deve informar um valor de " + min + " a " + max + ".");
            } catch (Exception e) {
                erro = true;
            }
        } while (ret < min || ret > max || erro);
        return ret;
    }

    public static String pedeStr(String msg, int min, int max) {
        String ret = "";
        boolean erro;
        do {
            erro = false;
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println(msg);
                ret = scan.next();
                if (ret.length() < min || ret.length() > max)
                    System.out.println("Você deve informar um literal de " + min + " caracteres a " + max + " caracteres.");
            } catch (Exception e) {
                erro = true;
            }
        } while (ret.length() < min || ret.length() > max || erro);
        return ret;
    }

    public static void menu() {
        System.out.println("1 - Inserir no início sem lista");
        System.out.println("2 - Inserir no início com lista");
        System.out.println("3 - Inserir no fim sem lista");
        System.out.println("4 - Inserir no fim com lista");
        System.out.println("5 - Verificar se existe na lista");
        System.out.println("6 - Remover específico da lista");
        System.out.println("7 - Remover do início da lista");
        System.out.println("8 - Remover do fim da lista");
        System.out.println("9 - Listar do primeiro ao último");
        System.out.println("10 - Listar do último ao primeiro");
        System.out.println("0 - Sair");

    }

    public static void main(String[] args) {
        int resp = 0;
        do {
            System.out.println("----------------------");
            menu();
            resp = pedeInt("Informe a opção:", 0, 10);
            switch (resp) {
                case 1:
                    insereSemLista();
                    break;
                case 2:
                    insereInicio();
                    break;
                case 3:
                    insereSemLista();
                    break;
                case 4:
                    insereFim();
                    break;
                case 5:
                    verificaExiste();
                    break;
                case 6:
                    removeEspecifico();
                    break;
                case 7:
                    removeInicio();
                    break;
                case 8:
                    removeFim();
                    break;
                case 9:
                    listaInicioFim();
                    break;
                case 10:
                    listaFimInicio();
                    break;
            }
        } while (resp != 0);
    }

}

