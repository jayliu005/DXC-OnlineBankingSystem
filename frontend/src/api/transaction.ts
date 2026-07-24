import { request } from '@/api/client'
import type {
  CashTransactionType,
  MoneyTransaction,
  MoneyTransactionRequest,
  TransferRequest,
} from '@/types/transaction'

export function createMoneyTransaction(
  transactionType: CashTransactionType,
  payload: MoneyTransactionRequest,
) {
  const path = transactionType === 'Deposit' ? 'deposits' : 'withdrawals'
  return request<MoneyTransaction>(`/api/transactions/${path}`, {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}

export function createTransfer(payload: TransferRequest) {
  return request<MoneyTransaction>('/api/transactions/transfers', {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}
