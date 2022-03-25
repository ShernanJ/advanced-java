export interface IPhone {
  id?: number;
  phone?: string | null;
}

export class Phone implements IPhone {
  constructor(public id?: number, public phone?: string | null) {}
}

export function getPhoneIdentifier(phone: IPhone): number | undefined {
  return phone.id;
}
