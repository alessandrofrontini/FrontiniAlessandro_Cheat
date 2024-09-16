

export class Utente {
    id?:number;
    nome?:string;
    cognome?:string;
    email?:string;
    user?:string;
    pwd?:string;
    salt?:string;

    constructor(nome:string, cognome:string, email:string, user:string, pwd:string){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.user = user;
        this.pwd = pwd;
    }
}
