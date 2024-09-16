export class Ricetta {
    id?:number;
    nome:string;
    prezzo:number;
    tempo:number;
    ingredienti:string;
    preparazione:string;
    idUtente?:number;

    constructor( nome:string, prezzo:number, tempo:number, ingredienti:string, preparazione:string){
        this.nome = nome;
        this.prezzo = prezzo;
        this.tempo = tempo;
        this.ingredienti = ingredienti;
        this.preparazione = preparazione;
    }

}