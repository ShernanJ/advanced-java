export class Student {

  // Since the ID is generated and managed by REST Project,
  // It is not required.

  // public id: number;
  public name: string;

  constructor (name: string) {
    this.name = name;
  }
}