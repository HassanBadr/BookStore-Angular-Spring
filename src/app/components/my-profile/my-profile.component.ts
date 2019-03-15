import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AppConst} from '../../constants/app-const';
import {UserService} from '../../services/user.service';
import {LoginService} from '../../services/login.service';
import {PaymentService} from '../../services/payment.service';
import {User} from '../../models/user';
import {UserBilling} from '../../models/user-billing';
import {UserPayment} from '../../models/user-payment';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  private serverPath = AppConst.serverPath;
  private dataFetched = false;
  private loggedIn: boolean;
  private loginError: boolean;
  private credential = {'username':'', 'password':''};

  private user: User = new User();
  private updateSuccess: boolean;
  private currentPassword: string;
  private newPassword: string;
  private incorrectPassword: boolean;

  private selectedProfileTap = 0;
  private selectedBillingTap = 0;

  private userPayment: UserPayment = new UserPayment();
  private userBilling: UserBilling = new UserBilling();
  private userPaymentList: UserPayment[] = [];
  private defaultPaymentSet: boolean;
  private defaultUserPaymentId: number;

  constructor(
    private loginService: LoginService,
    private userService: UserService,
    private paymentService: PaymentService,
    private router: Router
  ) { }

  selectedBillingChange(val: number){
    this.selectedBillingTap = val;
  }

  onUpdateUserInfo(){
    this.userService.updateUserInfo(this.user, this.newPassword, this.currentPassword).subscribe(
      res =>{
        console.log(res.text());
        this.updateSuccess = true;
      },
      err =>{
        console.log(err.text());
        let errorMessage = err.text();
        if(errorMessage === 'Incorrect current password!') this.incorrectPassword = true;
      }
    );
  }

  getCurrentUser(){
    this.userService.getCurrentUser().subscribe(
      res =>{
        this.user = res.json();
        this.dataFetched = true;
      },
      err =>{
        console.log(err);
      }
    );
  }

  onNewPayment(){
    this.paymentService.newPayment(this.userPayment).subscribe(
      res =>{
        this.getCurrentUser();
        this.selectedBillingTap = 0;
      },
      err =>{
        console.log(err.text());
      }
    );
  }

  onUpdatePayment(payment: UserPayment){
    this.userPayment = payment;
    this.userBilling = payment.userBilling;
    this.selectedBillingTap = 1;
  }

  onRemovePayment(id: number){
    this.paymentService.removePayment(id).subscribe(
      res =>{
        this.getCurrentUser();
      },
      err =>{
        console.log(err.text());
      }
    );
  }

  setDefaultPayment(){
    this.defaultPaymentSet  = false;
    this.paymentService.setDefaultPayment(this.defaultUserPaymentId).subscribe(
      res =>{
        this.getCurrentUser();
        this.defaultPaymentSet = true;
      },
      err =>{
        console.log(err.text());
      }
    );

  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res =>{
        this.loggedIn = true;
      },
      err =>{
        this.loggedIn = false;
        console.log('inactive session');
        this.router.navigate(['/myAccount']);
      }
    );

    this.getCurrentUser();

    this.userBilling.userBillingState = '';
    this.userPayment.type = '';
    this.userPayment.expiryMonth = '';
    this.userPayment.expiryYear = '';
    this.userPayment.userBilling = this.userBilling;
    this.defaultPaymentSet = false;
  }

}
