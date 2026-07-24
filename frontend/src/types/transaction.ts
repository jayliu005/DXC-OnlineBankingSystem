export type MoneyTransactionType = 'Deposit' | 'Withdraw'

export interface MoneyTransactionRequest {
  accountId: number
  amount: string
  securityPin: string
}

export interface MoneyTransaction {
  id: number
  transactionType: MoneyTransactionType
  transactionAmount: number
  accountFromId: number
  accountToId: number | null
  transactionTime: string
}
