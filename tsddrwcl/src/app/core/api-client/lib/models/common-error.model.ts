export class CommonError {
  code: number;
  message: string;
  longMessage: string;
  friendlyMessage: string;
  url: string;
  body?: any;

  constructor(
      {
          code = null,
          message = null,
          longMessage = null,
          friendlyMessage = null,
          url = null,
          body = null
      } = {}
  ) {
      this.code = code;
      this.message = message;
      this.longMessage = longMessage;
      this.friendlyMessage = friendlyMessage;
      this.url = url;
      this.body = body;
  }
}
