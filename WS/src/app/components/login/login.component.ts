import { Component, OnInit, Output, EventEmitter, Input, HostListener } from '@angular/core';
import { FormGroup, FormBuilder, Validators  } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SesionService } from '../../servicios/sesion.service';
import { NavbarComponent } from '../shared/navbar/navbar.component';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  navbar: NavbarComponent;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private sesionService:SesionService
    ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', [Validators.required]]
    });
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {

    this.submitted = true;
    this.sesionService.login(this.loginForm.value.email,this.loginForm.value.password).then( res => {
      this.sesionService.reiniciarCarrito();
      if(res === 'usuario'){
        this.sesionService.sesion = 'usuario' ;
        this.sesionService.sesionCambio.next('usuario');
        this.sesionService.id = this.loginForm.value.email ;
        this.sesionService.idCambio.next(this.loginForm.value.email);
        this.sesionService.loginSatisfactorio(this.loginForm.value.email,'usuario');
        this.router.navigate(['servicioss']);
      }else if(res === 'proveedor'){
        this.sesionService.sesion = 'proveedor';
          this.router.navigate(['servicioss']);
          this.sesionService.sesionCambio.next('proveedor');
          this.sesionService.id = this.loginForm.value.email ;
          this.sesionService.idCambio.next(this.loginForm.value.email);
          this.sesionService.loginSatisfactorio(this.loginForm.value.email,'proveedor');
      }else{
        alert('Usuario incorrecto');
      }
  })
  }

  registro(){
    this.router.navigate(['register']);
  }

}
