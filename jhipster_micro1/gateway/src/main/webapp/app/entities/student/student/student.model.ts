import dayjs from 'dayjs/esm';

export interface IStudent {
  id?: number;
  name?: string | null;
  email?: string | null;
  birthday?: dayjs.Dayjs | null;
}

export class Student implements IStudent {
  constructor(public id?: number, public name?: string | null, public email?: string | null, public birthday?: dayjs.Dayjs | null) {}
}

export function getStudentIdentifier(student: IStudent): number | undefined {
  return student.id;
}
