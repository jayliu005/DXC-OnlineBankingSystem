export type AccountType = 'Checking' | 'Saving'

export interface BankAccount {
  id: number
  accountType: AccountType
  accountBalance: number
  dateOfCreated: string
}

export interface CreateAccountRequest {
  accountType: AccountType | ''
  securityPin: string
  repeatSecurityPin: string
}
