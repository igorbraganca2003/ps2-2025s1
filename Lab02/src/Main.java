import banco.*;

class Main {
    public static void main(String[] args) {
       Conta[] contas = { 
        new ContaInfinita(1111, 50),
        new contaBancaria(2222, 99),
        new contaBancaria(3333, 1000),
        new ContaEspecial(4444, 800, 200)};
       for (int i=0; i<contas.length; i++){
        contas[i].sacar(900);
        System.out.print(contas[i]);
       }
    }

}