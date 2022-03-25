export interface IAddress {
  id?: number;
  street?: string | null;
  city?: string | null;
  provinceOrState?: string | null;
  country?: string | null;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public street?: string | null,
    public city?: string | null,
    public provinceOrState?: string | null,
    public country?: string | null
  ) {}
}

export function getAddressIdentifier(address: IAddress): number | undefined {
  return address.id;
}
