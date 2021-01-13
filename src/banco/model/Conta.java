package banco.model;

import banco.helper.Utils;

public class Conta {

    private static int codigo = 1001;

    private int numero;
    private Cliente cliente;
    private double saldo = 0.0;
    private double limite = 0.0;
    private double saldoTotal;

    public Conta(Cliente cliente) {
        this.numero = Conta.codigo;
        this.cliente = cliente;
        Conta.codigo++;
        this.atualizaSaldoTotal();
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
        this.atualizaSaldoTotal();
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    private void atualizaSaldoTotal(){
        this.saldoTotal = this.getSaldo() + this.getLimite();
    }

    public String toString(){
        return "Número da Conta: " + this.getNumero() +
                "\nCliente: " + this.cliente.getNome() +
                "\nSaldo Total: " + Utils.doubleparaString(this.getSaldoTotal());
    }

    public void depositar(Double valor){
        if(valor > 0){
            this.saldo = this.getSaldo() + valor;
            this.atualizaSaldoTotal();
            System.out.println("Depósito efetuado com sucesso!");
        } else{
            System.out.println("Erro ao efetuar o depósito. Tente Novamente.");
        }
    }

    public void sacar(Double valor){
        if(valor > 0 && this.getSaldoTotal() >= valor){
            if(this.getSaldo() >= valor){
                this.saldo = this.getSaldo() - valor;
                this.atualizaSaldoTotal();
                System.out.println("Saque efetuado com sucesso!");
            } else{
                Double restante = this.getSaldo() - valor;
                this.limite = this.getLimite() + restante;
                this.atualizaSaldoTotal();
                System.out.println("Saque efetuado com sucesso!");
            }
        } else{
            System.out.println("Saque não realizado. Tente Novamente.");
        }
    }

    public void transferir(Conta destino, Double valor) {
        if (valor > 0 && this.getSaldoTotal() >= valor) {
            if (this.getSaldo() >= valor) {
                this.saldo = this.getSaldo() - valor;
                destino.saldo = destino.getSaldo() + valor;
                this.atualizaSaldoTotal();
                destino.atualizaSaldoTotal();
                System.out.println("Transferência realizada com sucesso!");
            } else {
                Double restante = this.getSaldo() - valor;
                this.limite = this.getLimite() + restante;
                this.saldo = 0.0;
                destino.saldo = destino.getSaldo() + valor;
                this.atualizaSaldoTotal();
                destino.atualizaSaldoTotal();
                System.out.println("Transferência realizada com sucesso!");
            }
        } else {
            System.out.println("Transferência não realizada. Tente Novamente.");
        }

    }
}
