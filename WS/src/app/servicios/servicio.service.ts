
import { Injectable } from '@angular/core';

@Injectable()
export class ServicioService {

  private servicios:Servi[] = [
    {
      nombre: "Qiu Hotel Sukhumvit",
      pais: "Tailandia",
      ciudad:"Bangkok",
      descripcion:"Está a solo 3 minutos a pie de la estación de tren aéreo BTS de On Nut. Ofrece habitaciones amplias con TV vía satélite, una pileta al aire libre y WiFi gratis.",
      costo:1000,
      img: "assets/img/hotelPrueba.jpg",
      
    },
    {
      nombre: "Hotel Presidente",
      pais:"España",
      ciudad: "Benidorm",
      descripcion: "Hotel Presidente tiene como objetivo conseguir que tu visita sea lo más relajante y agradable posible, razón por la que tantos huéspedes siguen volviendo año tras año.",
      costo:1230,
      img: "assets/img/hotelPrueba2.jpg",
    },
    {
      nombre: "L Hotel",
      pais:"España",
      ciudad: "Islas canarias",
      descripcion:"Ideal para disfrutar de unas vacaciones en familia y conocer uno de los destinos turísticos más importantes del Mediterráneo",
      costo:560,
      img: "assets/img/hotelPrueba3.jpg",
    },
  ];

  constructor() {
    console.log("Servicio listo para usar!!!");
  }


  getServicios():Servi[]{
    return this.servicios;
  }

  getServicio( idx: string ){
    return this.servicios[idx];
  }

  buscarServicios( termino:string ):Servi[]{

    let serviciosArr:Servi[] = [];
    termino = termino.toLowerCase();

    for( let i = 0; i < this.servicios.length; i ++ ){

      let servicio = this.servicios[i];

      let nombre = servicio.nombre.toLowerCase();

      if( nombre.indexOf( termino ) >= 0  ){
        servicio.idx = i;
        serviciosArr.push( servicio )
      }

    }

    return serviciosArr;

  }


}


export interface Servi{
  nombre: string;
  pais: string;
  ciudad:string;
  descripcion:string;
  costo:number;
  img: string;
  idx?: number;
};