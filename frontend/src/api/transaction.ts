import { request } from '@/api/client'
import type {
  MoneyTransaction,
  MoneyTransactionRequest,
  MoneyTransactionType,
} from '@/types/transaction'

export function createMoneyTransaction(
  transactionType: MoneyTransactionType,
  payload: MoneyTransactionRequest,
) {
  const path = transactionType === 'Deposit' ? 'deposits' : 'withdrawals'
  return request<MoneyTransaction>(`/api/transactions/${path}`, {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}
