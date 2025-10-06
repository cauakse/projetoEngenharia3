import axios from 'axios';
import { compraCerta, vendaCerta, vendaErrada } from './dados.js';

const realizaUmaVendaQueTemProduto = async () => {
    try{
        console.log("Realizando uma venda que tem todos os produtos em estoque...");
        
        const response = await axios.post("http://localhost:8080/comercio/venda",vendaCerta, {
            headers:{
                'Content-Type': 'application/json'
            }
        });

        console.log("Sistema fez uma venda que tem produto: \n", response.data);
    }catch(e){
        console.log(e);
    }
}

const realizaUmaVendaQueNaoTemProduto = async () => {
    try{
         console.log("Realizando uma venda que tem um produto que não tem em estoque...");
        
        const response = await axios.post("http://localhost:8080/comercio/venda", vendaErrada, {
            headers:{
                'Content-Type': 'application/json'
            }
        });

        console.log("Sistema fez uma venda que não tem produto: \n", response.data);

    }catch(e){
        if(e.status === 400){
            console.log("Venda não realizada, estoque insuficiente, os clientes foram colocados na lista de espera.");
            return;
        }
        console.log(e);
    }
}

const realizaUmaCompraParaIndicarOsObservadores = async () => {
    try{
        console.log("Realizando uma compra para indicar os observadores...");
        
        const response = await axios.post("http://localhost:8080/comercio/compra", compraCerta, {
            headers:{
                'Content-Type': 'application/json'
            }
        });

        console.log("Sistema fez uma compra para indicar os observadores: \n", response.data);
    }catch(e){
        console.log(e);
    }
}

setTimeout(async ()=>{
    await realizaUmaVendaQueTemProduto();
}, 2000);


setTimeout(async ()=>{
    await realizaUmaVendaQueNaoTemProduto();
}, 12000);


setTimeout(async ()=>{
    await realizaUmaCompraParaIndicarOsObservadores();
}, 24000);
