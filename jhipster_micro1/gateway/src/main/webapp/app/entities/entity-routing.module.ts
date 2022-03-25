import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'student',
        data: { pageTitle: 'Students' },
        loadChildren: () => import('./student/student/student.module').then(m => m.StudentStudentModule),
      },
      {
        path: 'address',
        data: { pageTitle: 'Addresses' },
        loadChildren: () => import('./addressAndPhone/address/address.module').then(m => m.AddressAndPhoneAddressModule),
      },
      {
        path: 'phone',
        data: { pageTitle: 'Phones' },
        loadChildren: () => import('./addressAndPhone/phone/phone.module').then(m => m.AddressAndPhonePhoneModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
