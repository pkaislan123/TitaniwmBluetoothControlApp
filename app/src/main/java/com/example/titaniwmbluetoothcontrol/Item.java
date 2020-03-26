package com.example.titaniwmbluetoothcontrol;

class Item {
    private String nome;
    private String mac;
    private String tipo;
    private String statusConection;
    Item() {
        }

    public void setMac(String mac)
    {
        this.mac = mac;
    }


    public void setStatusConection(String statusConection)
    {
        this.statusConection = statusConection;
    }

    public String getStatusConection()
    {
        return this.statusConection;
    }


    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getMac()
    {
        return this.mac;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    // Implemente os Getters e Setters
}